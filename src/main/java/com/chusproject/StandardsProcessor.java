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

	/**
     *  Standards Processor Method 
     */
	public static int ccparser(String filename)
	{
		try{
			if (filename == "") {
				filename = "src/main/xml/Common-Core-Xml/math.xml";
			}
        	File fXmlFile = new File(filename);

        	// Using DOM to parse the XML file

        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(fXmlFile);
        	
        	doc.getDocumentElement().normalize();
        	 
        	System.out.println("Parse :" + filename);
        	
        	NodeList nList = doc.getElementsByTagName("LearningStandardItem");
        	
        	System.out.println("----------------------------");
        	
        	System.out.println("Number of LearningStandard Items: "+ nList.getLength());


        	//resule set to store all the parserItem

        	parserItem[] respi = new parserItem[nList.getLength()];
        	int tempindex = 0;
        	

        	// For each standard statement, generate on parserItem (as product in Factory mathod) and add into result set.

        	for (int temp = 0; temp < nList.getLength(); temp++) {
        		 
        		Node nNode = nList.item(temp);
         
        		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
         
        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
         
        			Element eElement = (Element) nNode;
        			String a = eElement.getElementsByTagName("RefURI").item(0).getTextContent();
        			String b = eElement.getElementsByTagName("StatementCode").item(0).getTextContent();
        			String c = eElement.getElementsByTagName("Statement").item(0).getTextContent();
        			
        			// Issue youtube search

        			Search currsearch = new Search();
        			String[] darr = currsearch.searchYoutube(eElement.getElementsByTagName("Statement").item(0).getTextContent());

        			parserItem pi = new parserItem(a,b,c, darr);
        			respi[tempindex] = pi;
        			tempindex++;
        			System.out.println(" Search completed: " + temp + "/" + nList.getLength());

        		}
        	}

        	System.out.println("============================================================");

        	//Print all the result

        	for (int temp = 0; temp < respi.length; temp++ ) {
        		respi[temp].printItem();
        	}

        	System.out.println(filename + "has been parsed!..\n\n\n");
        }catch(Exception e){
        	e.printStackTrace();
        }
        return 0;
	}

	 /**
     * Main entrance of the StandardsProcessor 
     */
    public static void main( String[] args )
    {
        System.out.println( "StandardsProcessor starts." );
        String foldername = "src/main/xml/Common-Core-Xml";

        //Open the dir to read all the file and parser it one by one
        //Even the xml file has the error, the loop will not end and automatically reject them.

        final File folder = new File(foldername);
        for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	     			// If the subDir is also required, it is easy to iterate here
	        } else {
	        	if(fileEntry.getName().toLowerCase().endsWith(".xml"))
		        	//Call parser here with the file name
		            ccparser(foldername + "/" + fileEntry.getName());
		    }
	    }
    }
}
