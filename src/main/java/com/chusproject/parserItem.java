package com.chusproject;


public class parserItem {
	private final int numOfVideo = 3; 
	
	private String refUri;
	private String sCode;
	private String statement;
	
	private String[] youtubeUrl = new String[numOfVideo]; 
	
	public parserItem(String a, String b, String c, String[] arr){
		refUri = a;
		sCode = b;
		statement = c;
		if (arr == null) {
			return;
		}
		for (int temp = 0; temp < arr.length; temp++)
		{
			if ((arr[temp] != null) && (temp < 3)) 
				youtubeUrl[temp] = arr[temp];
		}
	};
	
	
	
	public int printItem(){
		System.out.println("--------------------------------------------------------------");
		System.out.println(sCode + " - " + statement + "\n" + refUri);
		System.out.println("--------------------------------------------------------------");
		if( youtubeUrl != null){
			for(int temp = 0; temp< youtubeUrl.length; temp++)
			{
				if (youtubeUrl[temp] != null)
					System.out.println(youtubeUrl[temp]);
			}	
		}
		
		System.out.println("\n");
		return 0;
	}
}
