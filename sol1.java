package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class main_misc {
	
	
	public static final int MAX = 1_000_000;
	
	
	public static class unit{
		
		public int freq;
		public String name;
		
		public unit(String name,int freq) {
			this.freq = freq;
			this.name = name;
		}
		
		public String toString() {
			
			return "("+name+","+freq+")";
			
		}
		
	}
	
	public static void main(String[] args) {
		
	List<unit> freq = new ArrayList<>();
	freq.add(new unit("John",15));
	freq.add(new unit("Jon",12));
	freq.add(new unit("Chris",13));
	freq.add(new unit("Kris",4));
	freq.add(new unit("Christopher",19));
	
	List<String> s0 = new ArrayList<>(Arrays.asList("John","Jon"));
	List<String> s1 = new ArrayList<>(Arrays.asList("John","Johnny"));
	List<String> s2 = new ArrayList<>(Arrays.asList("Chris","Kris"));
	List<String> s3 = new ArrayList<>(Arrays.asList("Chris","Christopher"));
	
	List<List<String>> synonyms = new ArrayList<>(Arrays.asList(s0,s1,s2,s3));
	
	System.out.println(freq_names(freq,synonyms));
	
	}
	
	
	
	private static List<unit> freq_names(List<unit> freq,List<List<String>> synonyms){
		
		HashMap<String,Integer> map = new HashMap<>();
		
		int curr = 0;
		
		for(int i = 0;i < synonyms.size();i++){
			
			//just creating a reference
			List<String> ref = synonyms.get(i);
			
			int val = -1;
			
			for(int j = 0;j < 2;j++){
				
				String str = ref.get(j);
				
				if(map.containsKey(str)){
					val = map.get(str);
				}
				
			}
			if(val == -1) {
				val = curr;
				curr++;
			}
			for(int j = 0;j < 2;j++){
				
				String str = ref.get(j);
				
				map.put(str, val);
			}
			
		}
		
		List<unit> real_freq = new ArrayList<>();
		
		for(int i = 0;i < curr;i++){
			real_freq.add(new unit("",0));
		}
		
		for(int i = 0;i < freq.size();i++){
			
			String name = freq.get(i).name;
			int qnt = freq.get(i).freq;
			
			int indx = map.get(name);
			
			unit u = real_freq.get(indx);
			
			if(u.name.equals("")) {
				u.name = name;
			}
			u.freq += qnt;
		}
		
		
		return real_freq;
	}
	
	

}
