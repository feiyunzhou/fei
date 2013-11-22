package com.rex.crm.reporter;

import java.util.logging.Level;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.core.internal.registry.RegistryProviderFactory;

import com.rex.crm.util.CRMUtility;

public class ReportEngineFactory {

    private static IReportEngine birtEngine = null;
    private static Object syncObject_  =  new Object();
    
    public static IReportEngine  getBirtReportEngine(){
        if(birtEngine == null){
            
            synchronized(syncObject_) {
                
                if (birtEngine == null) {
                    try{
                        
                        final EngineConfig config = new EngineConfig( );
                        config.setEngineHome( CRMUtility.getReportEngineHome() );
                        config.setLogConfig(CRMUtility.getReportLog(), Level.ALL);
                                
                        Platform.startup( config );  //If using RE API in Eclipse/RCP application this is not needed.
                        IReportEngineFactory factory = (IReportEngineFactory) Platform
                                .createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
                        birtEngine = factory.createReportEngine( config );
                        birtEngine.changeLogLevel( Level.WARNING );
                    }catch(Exception e){
                        e.printStackTrace();
                    }                  

                }

              }
  
        }
        
        return birtEngine;
    }
    
    public static void destoryBirtPlat(){
        if(birtEngine!=null)
            birtEngine.destroy();
        Platform.shutdown();
        //Bugzilla 351052
        RegistryProviderFactory.releaseDefault();
    }
}
