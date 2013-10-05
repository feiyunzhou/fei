package com.rex.crm.reporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class ReporterMain {
    @Option(name="-rn",usage="reporting name") 
    private String reporting_name = "";

    @Option(name="-sd",usage="sarting date") 
    private String start_date;
    
    @Option(name="-ed",usage="ending date") 
    private String end_date;
    
   // receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<String>();

    
    public void doMain(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        
        // if you have a wider console, you could increase the value;
        // here 80 is also the default
        parser.setUsageWidth(100);

        try {
            // parse the arguments.
            parser.parseArgument(args);

            // you can parse additional arguments if you want.
            // parser.parseArgument("more","args");

            // after parsing arguments, you should check
            // if enough arguments are given.
            if( arguments.isEmpty() )
                throw new CmdLineException(parser,"No argument is given");

        } catch( CmdLineException e ) {
            // if there's a problem in the command line,
            // you'll get this exception. this will report
            // an error message.
            System.err.println(e.getMessage());
            System.err.println("java SampleMain [options...] arguments...");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            // print option sample. This is useful some time
            //System.err.println("  Example: java SampleMain"+parser.printExample(ALL));

            return;
        }

     

        if( !reporting_name.isEmpty() ){
           if(reporting_name.equalsIgnoreCase("visiting")){
               
           } 
        }
           

        // access non-option arguments
        System.out.println("other arguments are:");
        for( String s : arguments )
            System.out.println(s);
    } 
    
    public static void main(String[] args) throws IOException {
        new ReporterMain().doMain(args);

    }

    
    
}
