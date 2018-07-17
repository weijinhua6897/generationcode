package com.wjh.generationcode.GeneratorConfiguration;

import com.mysql.jdbc.Messages;
import com.wjh.generationcode.bean.FileUrl;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.SimpleXMLMapperGenerator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class MySimpleXMLMapperGenerator extends SimpleXMLMapperGenerator {
    static FileUrl fileUrl;
    @Override
    protected XmlElement getSqlMapElement() {
        FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
        this.progressCallback.startTask(Messages.getString("Progress.12", new String[]{table.toString()}));
        XmlElement answer = new XmlElement("mapper");
        String namespace = this.introspectedTable.getMyBatis3SqlMapNamespace();
        answer.addAttribute(new Attribute("namespace", namespace));
        this.context.getCommentGenerator().addRootComment(answer);
        this.addResultMapElement(answer);
        this.addDeleteByPrimaryKeyElement(answer);
        this.addInsertElement(answer);
        this.addUpdateByPrimaryKeyElement(answer);
        this.addSelectByPrimaryKeyElement(answer);
        this.addSelectAllElement(answer);
        //this.addCountListElement(answer);
       // this.addQueryPageListElement(answer);
        getProperties();
        fileUrl.setBeanurl(introspectedTable.getBaseRecordType());
        try {
            genDAO( introspectedTable.getTableConfiguration().getDomainObjectName(),"Integer","id");
            genController( introspectedTable.getTableConfiguration().getDomainObjectName(),"Integer","id");
            genService( introspectedTable.getTableConfiguration().getDomainObjectName(),"Integer","id");
            genServiceImpl( introspectedTable.getTableConfiguration().getDomainObjectName(),"Integer","id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;

    }

        public static void genController(String name ,String primaryType, String primaryKey) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("controllerUrl", fileUrl.getControllerurl());
        paramMap.put("serviceUrl", fileUrl.getServiceurl());
        paramMap.put("url", fileUrl.getBeanurl());
        paramMap.put("name", name);
        paramMap.put("primaryType", primaryType);
        paramMap.put("primaryKey", primaryKey);
        paramMap.put("capitalName", name);
        genFile(paramMap,fileUrl.getControllerurl(),"Controller","controller.vm");
    }
    public static void genService(String name ,String primaryType, String primaryKey) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("serviceUrl", fileUrl.getServiceurl());
        paramMap.put("url", fileUrl.getBeanurl());
        paramMap.put("name", name);
        paramMap.put("primaryType", primaryType);
        paramMap.put("primaryKey", primaryKey);
        genFile(paramMap,fileUrl.getServiceurl(),"Service","service.vm");
    }
    public static void genServiceImpl(String name ,String primaryType, String primaryKey) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("serviceImplUrl", fileUrl.getServiceImplurl());
        paramMap.put("serviceUrl", fileUrl.getServiceurl());
        paramMap.put("mapperUrl", fileUrl.getDaourl());
        paramMap.put("url", fileUrl.getBeanurl());
        paramMap.put("name", name);
        paramMap.put("primaryType", primaryType);
        paramMap.put("primaryKey", primaryKey);
        genFile(paramMap,fileUrl.getServiceImplurl(),"ServiceImpl","serviceImpl.vm");
    }
    public static void genDAO(String name ,String primaryType, String primaryKey) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("mapperUrl", fileUrl.getDaourl());
        paramMap.put("url", fileUrl.getBeanurl());
        paramMap.put("name", name);
        paramMap.put("primaryType", primaryType);
        paramMap.put("primaryKey", primaryKey);
        genFile(paramMap,fileUrl.getDaourl(),"Dao","mapper.vm");
    }


    public static void genFile(Map<String, Object> paramMap,String fileurl,String filesuffix,String filetemplate) throws Exception {
        //创建一个合适的Configration对象
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(Configuration.VERSION_2_3_23 );
        configuration.setDirectoryForTemplateLoading(new File(".\\src\\main\\resources\\template"));
        configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码
        //获取或创建一个模版。
        Template template = configuration.getTemplate(filetemplate);
        File file =new File(".\\src\\main\\java\\"+fileurl.replace(".","\\\\"));
        if(!file.exists()){
            file.mkdirs();
        }
        Writer writer  = new OutputStreamWriter(new FileOutputStream(".\\src\\main\\java\\"+fileurl.replace(".","\\\\")+"\\"+paramMap.get("name")+filesuffix+".java" ),"UTF-8");
        template.process(paramMap, writer);
        System.out.println("恭喜，生成成功~~");
    }


    public static void getProperties(){
        Properties properties = new Properties();
        InputStream in =  MySimpleJavaClientGenerator.class.getResourceAsStream("/application.properties");//加载 application.properties资源文件，如果该文件在包内则加包名
        try {
            properties.load(in);
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        fileUrl=new FileUrl();
        fileUrl.setControllerurl(properties.getProperty("geneator.controller"));
        fileUrl.setDaourl(properties.getProperty("geneator.dao"));
        fileUrl.setServiceImplurl(properties.getProperty("geneator.serviceImpl"));
        fileUrl.setServiceurl(properties.getProperty("geneator.service"));
    }
}
