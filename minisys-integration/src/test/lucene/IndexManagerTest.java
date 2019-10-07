package lucene;

import com.moon.minisys.busi.dao.BookDao;
import com.moon.minisys.busi.pojo.Book;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.lucene.document.Field.Store;

/**
 * Lucene
 * @author yujiangtao
 * @date 2018/7/23 23:32
 */
public class IndexManagerTest {

    ApplicationContext applicationContext = null;

    @Before
    public void setUp() {
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/applicationContext-dao.xml", "spring/applicationContext-jedis.xml", "spring/applicationContext-service.xml");
    }

    @Test
    public void createIndex() throws IOException {
        BookDao bookDao = (BookDao) applicationContext.getBean("bookDao");

        // 采集数据
        List<Book> books = bookDao.queryBooks();

        // 将采集到的数据封装到Document对象中
        List<Document> documentList = new ArrayList<Document>();
        Document document;
        for(Book book : books) {
            document = new Document();
            // store:如果是yes，则存储到文档域中
            // 不分词，索引，存储 StringField
            Field id = new StringField("id", book.getId() + "", Store.YES);
            // 分词、索引、存储 TextField
            Field name = new TextField("name", book.getName(), Store.YES);
            // 分词、索引、存储 数字类型使用float FloatField
            Field price = new FloatField("price", book.getPrice(), Store.YES);
            // 不分词、不索引、存储 StoredField
            Field pic = new StoredField("pic", book.getPic());
            // 分词索引、不存储 TextField
            Field description = new TextField("description", book.getDescription(), Store.NO);

            // 设boost值（影响排序）
            if(book.getId() == 4) {
                description.setBoost(100f);
            }

            document.add(id);
            document.add(name);
            document.add(price);
            document.add(pic);
            document.add(description);
            documentList.add(document);
        }

        // 创建分词器，标准分词器
        //Analyzer analyzer = new StandardAnalyzer();

        // 中文分词器
        Analyzer analyzer = new IKAnalyzer();

        // 创建IndexWriter
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        File indexFile = new File("E:\\lucene\\");
        Directory directory = FSDirectory.open(indexFile);
        IndexWriter writer = new IndexWriter(directory, config);

        // 通过IndexWriter对象将Document写入到索引库中
        for(Document doc : documentList) {
            writer.addDocument(doc);
        }

        // 关闭writer
        writer.close();
    }

    @Test
    public void deleteIndex() throws IOException {
        // 创建IndexWriter
        Directory directory = FSDirectory.open(new File("E:\\lucene"));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        IndexWriter writer = new IndexWriter(directory, cfg);

        // Term是索引域中最小的单位
        writer.deleteDocuments(new Term("id", "1"));

        // 删除所有（慎用）
        //writer.deleteAll();

        writer.close();
    }

    @Test
    public void updateIndex() throws Exception {
        // 创建IndexWriter
        Directory directory = FSDirectory.open(new File("E:\\lucene"));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        IndexWriter writer = new IndexWriter(directory, cfg);

        // 第一个参数：指定查询条件
        // 第二个参数：修改后的对象
        // 修改时如果根据查询条件，可以查询出结果，则将以前的删除，然后覆盖，没有则新增
        Document doc = new Document();
        doc.add(new TextField("name", "lisi", Store.YES));
        writer.updateDocument(new Term("name", "zhangsan"), doc);

        writer.close();
    }
}
