package com.chusproject;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;

public class StandardsProcessor
{


	public static int ccparser(String filename)
	{
		try{
			if (filename != "") {
				filename = "src/main/xml/Common-Core-Xml/math.xml";
			}
        	File fXmlFile = new File(filename);
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(fXmlFile);
        	
        	doc.getDocumentElement().normalize();
        	 
        	System.out.println("Parse :" + filename);
        	
        	NodeList nList = doc.getElementsByTagName("LearningStandardItem");
        	
        	System.out.println("----------------------------");
        	
        	System.out.println("Number of LearningStandard Items: "+ nList.getLength());

        	parserItem[] respi = new parserItem[nList.getLength()];
        	int tempindex = 0;
        	
        	for (int temp = 0; temp < nList.getLength(); temp++) {
        		 
        		Node nNode = nList.item(temp);
         
        		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
         
        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
         
        			Element eElement = (Element) nNode;
        			//System.out.println(eElement.getElementsByTagName("RefURI").item(0).getTextContent());
        			//System.out.println(eElement.getElementsByTagName("StatementCode").item(0).getTextContent());
        			//System.out.println(eElement.getElementsByTagName("Statement").item(0).getTextContent());
        			String a = eElement.getElementsByTagName("RefURI").item(0).getTextContent();
        			String b = eElement.getElementsByTagName("StatementCode").item(0).getTextContent();
        			String c = eElement.getElementsByTagName("Statement").item(0).getTextContent();

        			Search currsearch = new Search();
        			parserItem pi = new parserItem(a,b,c, currsearch.searchYoutube(eElement.getElementsByTagName("Statement").item(0).getTextContent()));
        			respi[tempindex] = pi;
        			tempindex++;
        			System.out.println(" Search completed: " + temp + "/" + nList.getLength());

        		}
        	}

        	System.out.println("============================================================");

        	for (int temp = 0; temp < respi.length; temp++ ) {
        		respi[temp].printItem();
        	}

        	System.out.println(filename + "has been parsed!..\n\n\n");
        }catch(Exception e){
        	e.printStackTrace();
        }
        return 0;
	}


    public static void main( String[] args )
    {
        System.out.println( "StandardsProcessor starts." );
        String foldername = "src/main/xml/Common-Core-Xml";
        final File folder = new File(foldername);
        for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	     			
	        } else {
	            //System.out.println(fileEntry.getName());
	            ccparser(foldername+fileEntry.getName());
	        }
	    }

        //ccparser("src/main/xml/Common-Core-Xml/math.xml");
		
		//ccparser("src/main/xml/Common-Core-Xml/ela-literacy.xml");
        

    }
}
