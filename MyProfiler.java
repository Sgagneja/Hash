/**
 * Filename:   MyProfiler.java
 * Project:    p3b-201901     
 * Authors:    Shaan Gagneja Lecture 002
 *
 * Semester:   Spring 2019
 * Course:     CS400
 * 
 * Due Date:   Thu Mar 28 11:59 PM
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       TODO: add any known bugs, or unsolved problems here
 */

// Used as the data structure to test our hash table against
import java.util.TreeMap;

public class MyProfiler<K extends Comparable<K>, V> {

    HashTableADT<K, V> hashtable;
    TreeMap<K, V> treemap;
    
    /**
     * creates a HashTable and a TreeMap
     */
    public MyProfiler() {
        // TODO: complete the Profile constructor
        // Instantiate your HashTable and Java's TreeMap
    	this.hashtable = new HashTable<K,V>();
    	this.treemap = new TreeMap<K,V>();
    }
    
    /**
     * Inserts for both the Hash Table and Tree Map
     * @param key the key
     * @param value the value
     */
    public void insert(K key, V value) {
        // TODO: complete insert method
        // Insert K, V into both data structures
    	try {
			hashtable.insert(key,value);
			treemap.put(key,value);
		} catch (IllegalNullKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
     * retrieves for both the Hash Table and Tree Map
     * @param key the key
     */
    public void retrieve(K key) {
        // TODO: complete the retrieve method
        // get value V for key K from data structures
    	try {
			hashtable.get(key);
			treemap.get(key);
		} catch (IllegalNullKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
     * removes for both the Hash Table and Tree Map
     * @param key the key
     */
    public void remove(K key) {
    	try {
			hashtable.remove(key);
			treemap.remove(key);
		} catch (IllegalNullKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * runs all of the methods based on the the numElements
     * @param args the list of arguments
     */
    public static void main(String[] args) {
        try {
            int numElements = Integer.parseInt(args[0]);
            MyProfiler<Integer, Integer> profile = new MyProfiler<Integer, Integer>();
            for(int i = 0; i < numElements; i++) {
            	profile.insert(i, i);
            }
            for(int i = 0; i < numElements; i++) {
            	profile.retrieve(i);
            }
            for(int i = 0; i < numElements; i++) {
            	profile.remove(i);
            }
            
            // TODO: complete the main method. 
            // Create a profile object. 
            // For example, Profile<Integer, Integer> profile = new Profile<Integer, Integer>();
            // execute the insert method of profile as many times as numElements
            // execute the retrieve method of profile as many times as numElements
            // See, ProfileSample.java for example.
            
        
            String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
            System.out.println(msg);
        }
        catch (Exception e) {
            System.out.println("Usage: java MyProfiler <number_of_elements>");
            System.exit(1);
        }
    }
}
