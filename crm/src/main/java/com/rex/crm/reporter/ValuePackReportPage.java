package com.rex.crm.reporter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;


import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.Response;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;

import com.rex.crm.TemplatePage;
import com.rex.crm.util.CRMUtility;


/**
 * @author Feiyun Zhou 
 */
public class ValuePackReportPage extends TemplatePage{
	private static final Logger logger = Logger.getLogger(ValuePackReportPage.class);
	/**
	 * Constructor
	 */
	
	public ValuePackReportPage(){
	    init("all.rptdesign");
	   // setMenuActive(0);
	}
	
    public ValuePackReportPage(String reportName){
        init(reportName);   
    }
	
	public void init(String reportName)
	{
	    
	    try {
	        System.out.print("starting rendering...");
    	    IReportEngine engine = ReportEngineFactory.getBirtReportEngine();
	   
            IReportRunnable design  = engine.openReportDesign( CRMUtility.getReportDesignFolder()+File.separator+reportName );
            
            
            IRunAndRenderTask task = engine.createRunAndRenderTask(design); 
            // engine.createRenderTask(design);
             
            
            task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, ValuePackReportPage.class.getClassLoader()); 
           
            HTMLRenderOption options = new HTMLRenderOption();    
            
            //File tmpfile = File.createTempFile("rpt_", ".html", new File( Configuration.getReportOutputFolder()));
            final File tmpfile = File.createTempFile("rpt"+System.currentTimeMillis()+"_"+(int)1000*Math.random(), ".html", new File( CRMUtility.getReportOutputFolder()));
            logger.debug("tmp file:"+tmpfile.getAbsolutePath());
            
            FileOutputStream tempfileos = new FileOutputStream(tmpfile);
            options.setOutputStream(tempfileos);
            options.setOutputFormat("html");
            options.setImageDirectory(CRMUtility.getReportImagesFolder());
            options.setBaseImageURL(CRMUtility.getReportBaseImgURL());
            options.setImageHandler(new HTMLServerImageHandler());

            
            //Setting this to true removes html and body tags
            //options.setEmbeddable(true);
            //PDFRenderOption options = new PDFRenderOption();
            //options.setOutputFormat("pdf");
            //File tmpfile = File.createTempFile("rpt_", ".pdf", new File( Configuration.getReportOutputFolder()));
            //options.setOutputStream(new FileOutputStream(tmpfile));

            task.setRenderOption(options);
            //run and render report
            //task.
            task.run();
            task.close();

           logger.debug("file generated:"+tmpfile.getAbsolutePath());
	    
           tempfileos.close();
           //add(new DocumentInlineFrame("report_frame", new FileResourceStream(tmpfile)));
            //add(new WebMarkupContainer("report_frame"));
          // WebMarkupContainer mag_report_frame = new WebMarkupContainer("report_frame");
          // add(mag_report_frame);
          // mag_report_frame.add( new AttributeModifier("src",new Model(CRMUtility.getReportBaseURL()+tmpfile.getName())));
           
           add(
                   new WebComponent("report_container") {

                       @Override
                       protected void onComponentTag(ComponentTag tag) {
                           Response response = getRequestCycle().getResponse();
                           try {
                               String report_html = com.google.common.io.Files.toString(tmpfile, Charset.forName("utf-8"));
                               response.write(report_html);
                           } catch (IOException e) {
                               // TODO Auto-generated catch block
                               e.printStackTrace();
                           }

                       }
           });
           
	    } catch (EngineException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 



	}
	
	
}