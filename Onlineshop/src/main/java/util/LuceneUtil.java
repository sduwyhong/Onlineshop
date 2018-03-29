package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import entity.Item;

public class LuceneUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(LuceneUtil.class);
	public static final String FIELD_ITEM_NAME = "item_name";
	//lucene版本号
	private static final Version version = Version.LUCENE_44;
	//存放文件夹地址
	private Directory directory = null;
	//
	private File path = null;
	//读取文件夹类
	private DirectoryReader ireader = null;
	//建立索引类
	private IndexWriter iwriter = null;
	//中文分词器
	private IKAnalyzer analyzer;
	//构造方法
	public LuceneUtil() {
		directory = new RAMDirectory();
		path = new File("E:/SSMOnlineShop/lucene");
		try {
			directory =  FSDirectory.open(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//获取搜索器
	public IndexSearcher getSearcher(){
		try {
			if(ireader==null) {
				//读取文件夹索引文件
				ireader = DirectoryReader.open(directory);
			} else {
				DirectoryReader tr = DirectoryReader.openIfChanged(ireader) ;
				if(tr!=null) {
					ireader.close();
					ireader = tr;
				}
			}
			//返回绑定了索引文件读取器的搜索器
			return new IndexSearcher(ireader);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private IKAnalyzer getAnalyzer(){
		if(analyzer == null){
			return new IKAnalyzer();
		}else{
			return analyzer;
		}
	}
	//创建索引
	public boolean dirIsEmpty(){
		if(path.list().length==0){
			return true;
		}else{
			return false;
		}
	}
	public void createIndex(Item item) {
		try {
			IndexWriterConfig iwConfig =  new IndexWriterConfig(version, getAnalyzer());
			iwConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
			iwriter = new IndexWriter(directory,iwConfig);
			int id = item.getId();
			String name = item.getName();
			Document doc = new Document();
			doc.add(new TextField("id", id+"",Field.Store.YES));
			doc.add(new TextField(FIELD_ITEM_NAME, name,Field.Store.YES));
			iwriter.addDocument(doc);
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(iwriter != null)
					iwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	public void createIndex(List<Item> items){
		try {
			//配置索引建立类---版本，分词器
			IndexWriterConfig iwConfig =  new IndexWriterConfig(version, getAnalyzer());
			//？？？
			iwConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
			//绑定了索引文件存放地址和建立索引所需配置的索引建立器
			iwriter = new IndexWriter(directory,iwConfig);
			//将结果集需要的属性建立索引并写入文件
			for (Item item : items) {
				int id = item.getId();
				String name = item.getName();
				Document doc = new Document();
				doc.add(new TextField("id", id+"",Field.Store.YES));
				doc.add(new TextField(FIELD_ITEM_NAME, name,Field.Store.YES));
				iwriter.addDocument(doc);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(iwriter != null)
					iwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	//更新
	public Document getDocByItem(Item item){
		//将article信息注入到document
		Document doc = new Document();
		doc.add(new TextField("id", item.getId()+"",Field.Store.YES));
		doc.add(new TextField(FIELD_ITEM_NAME, item.getName(),Field.Store.YES));
		return doc;
	}
	public void updateIndex(String field,String value,Item item) throws Exception{
		//配置索引建立类---版本，分词器
		IndexWriterConfig iwConfig =  new IndexWriterConfig(version, getAnalyzer());
		//绑定了索引文件存放地址和建立索引所需配置的索引建立器
		iwriter = new IndexWriter(directory,iwConfig);
		Term term = new Term(field,value);
		Document doc = getDocByItem(item);
		iwriter.updateDocument(term, doc);
		iwriter.close();
	}
	//删除
	public void delIndex(String field,String value) throws IOException{
		//配置索引建立类---版本，分词器
		IndexWriterConfig iwConfig =  new IndexWriterConfig(version, getAnalyzer());
		//绑定了索引文件存放地址和建立索引所需配置的索引建立器
		iwriter = new IndexWriter(directory,iwConfig);
		iwriter.deleteDocuments(new Term(field,value));
		iwriter.close();
	}
	//根据keyword关键字，搜索field字段，返回num个结果
	public int[] searchByTerm(String field,String keyword,int num) throws org.apache.lucene.queryparser.classic.ParseException{
		//获取搜索器
		IndexSearcher isearcher = getSearcher();
		//获取分词器
		Analyzer analyzer =  getAnalyzer();
		//使用QueryParser查询分析器构造Query对象（版本、字段、分词器）
		QueryParser qp = new QueryParser(version,
				field,analyzer);
		//这句所起效果？
		qp.setDefaultOperator(QueryParser.OR_OPERATOR);
		try {
			//解析关键字
			Query query = qp.parse(keyword);
			//击中文件索引数组
			ScoreDoc[] hits;
			//注意searcher的几个方法
			hits = isearcher.search(query, null, num).scoreDocs;
			int[] ids = new int[hits.length];
			LOGGER.info("target ids are =");
			for (int i = 0; i < hits.length; i++) {
				Document doc = isearcher.doc(hits[i].doc);
				String id = doc.get("id");
				LOGGER.info(id+" ");
				ids[i] = Integer.parseInt(id);
			}
			return ids;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
