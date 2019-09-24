//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P3b
// Files:           HashTable.java, HashTableTest.java
//
// Course:          CS 400, Spring Term 2019
//
// Author:          Shaan Gagneja
// Email:           sgagneja@wisc.edu
// Lecturer's Name: Debra Deppeler
//
///////////////////////////////////////////////////////////////////////////////
// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: Linear Probing.
// identify your scheme as open addressing or bucket
//
// TODO: Used built in hashing method and % by table index to find the index.
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture
//
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
  // TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
  private int numKeys; //number of keys inputed
  private int capacity; //current capacity of the array
  private double lft; //load factor threshold
  private Object[] keys;
  private Object[] vals;
  private int lowest;
		
  /**
   * Creates a HashTable with a capacity of 10 and a load factor threshold of 0.9	
   */
  public HashTable() {
	  this.numKeys = 0;
	  this.capacity = 10;
	  this.lft = 0.9;
	  keys = new Object[this.capacity];
	  vals = new Object[this.capacity];
	  lowest = this.capacity-1;
  }
	
  /**
   * Creates a HashTable 
   * @param initialCapacity	the initial capacity of the table
   * @param loadFactorThreshold	the load factor threshold for the table
   */
  public HashTable(int initialCapacity, double loadFactorThreshold) {
	  this.numKeys = 0;
      this.capacity = initialCapacity;
      this.lft = loadFactorThreshold;
      keys = new Object[this.capacity];
	  vals = new Object[this.capacity];
	  lowest = this.capacity-1;
  }

  /**
   * Inserts a key and value into the table based on the hash function
   * @param key		the key
   * @param value	the value
   * @throws IllegalNullKeyException, DuplicateKeyException
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
	  // TODO Auto-generated method stub
	  if(key == null) {
		  throw new IllegalNullKeyException();
	  }
	  try {
		  get(key);
		  throw new DuplicateKeyException();
	  }
	  catch(KeyNotFoundException e){
		  if (this.getLoadFactor() >= this.getLoadFactorThreshold()) {
			  Object[] newKeys = new Object[this.capacity * 2 + 1];
		      Object[] newVals = new Object[this.capacity * 2 + 1];
		      int newCapacity = this.capacity * 2 + 1;
		      lowest = newCapacity;
		      for (int i = 0; i < this.capacity; i++) {
		          if (this.keys[i] != null) {
		        	  int hashCode = this.keys[i].hashCode();
		        	  if(hashCode<0) {
		        		  hashCode = Math.abs(hashCode);
		        	  }
		        	  int hashIndex = hashCode % (newCapacity);
		              // collision in the data
		        	  // re input into the new array with linear probing
		              boolean repeat = false;
		              for (int a = hashIndex; a < newCapacity; a++) {
		            	  if (a == newCapacity - 1 && !repeat) {
		            		  a = 0;
		            		  repeat = true;
		            	  } else if (repeat) {
		            		  break;
		            	  }
		            	  if (newKeys[a] == null) {
		            		  newKeys[a] = this.keys[i];
		            		  newVals[a] = this.vals[i];
		            		  if(a < lowest) {
		            			  a = lowest;
		            		  }
		            		  break;
		            	  }
		              }
		          }
		      }	
		      this.keys = newKeys;
		      this.vals = newVals;
		      this.capacity = this.capacity * 2 + 1;
		  }

		  int hashCode = key.hashCode();
		  if(hashCode<0) {
    		  hashCode = Math.abs(hashCode);
    	  }
		  int index = hashCode % this.capacity;
		  // collision in the data
		  // linear probing 
		  boolean repeat = false;
		  for (int i = index; i < this.capacity; i++) {
			  if (i == this.capacity - 1 && !repeat) {
		          i = lowest;
		          repeat = true;
		      } else if (repeat) {
		          break;
		      }
		      if (this.keys[i] == null) {
		          this.keys[i] = key;
		          this.vals[i] = value;
		          this.numKeys++;
		          if(i < lowest) {
		        	  lowest = i;
		          }
		          break;
		      }
		  }
	  }
  }

  /**
   * Removes a key and its value from their respective arrays
   * @param key	the key to be removed
   * @throws IllegalNullKeyException
   * @return true if removed, false if not 
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
	  // TODO Auto-generated method stub
	  if(key == null) {
		  throw new IllegalNullKeyException();
	  }
	  int hashCode = key.hashCode();
	  if(hashCode<0) {
		  hashCode = Math.abs(hashCode);
	  }
	  int hashIndex = hashCode % (this.capacity);
	  boolean repeat = false;
	  for (int i = hashIndex; i < this.capacity; i++) {
		  if (i == this.capacity - 1 && !repeat) {
			  i = lowest;
			  repeat = true;
		  } else if (repeat) {
			  break;
		  }
		  if (this.keys[i] != null) {
			  if (key.compareTo((K) this.keys[i]) == 0) {
				  this.keys[i] = null;
				  this.vals[i] = null;
				  this.numKeys--;
				  return true;
			  }
		  }
	  }
	  return false;
  }

  /**
   * Gets a value given a key
   * @param key the key being used to find a value
   * @throws IllegalNullKeyException, KeyNotFoundException
   * @return the value for the given key
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
	  // TODO Auto-generated method stub
	  if (key == null) {
	      throw new IllegalNullKeyException();
	  }
	  int hashCode = key.hashCode();
	  if(hashCode < 0) {
		  hashCode = Math.abs(hashCode);
	  }
	  int hashIndex = hashCode % (this.capacity);
	  boolean repeat = false;
	  for (int i = hashIndex; i < this.capacity; i++) {
	      if (i == this.capacity-1 && !repeat) {
	    	  i = lowest;
	    	  repeat = true;
	      } else if (repeat) {
	    	  break;
	      }
	      if (this.keys[i] != null) {
	    	  if (key.compareTo((K) this.keys[i]) == 0) {
	    		  return ((V) this.vals[i]);
	    	  }
	      	}
	  }
	  throw new KeyNotFoundException();
  }

  /**
   * returns the number of keys inputed
   * @return the number of keys
   */
  @Override
  public int numKeys() {
	  // TODO Auto-generated method stub
	  return numKeys;
  }

  /**
   * gets the load factor threshold
   * @return the load factor threshold
   */
  @Override
  public double getLoadFactorThreshold() {
	  // TODO Auto-generated method stub
	  return this.lft;
  }

  /**
   * gets the load factor of the current array
   * @return the current load factor
   */
  @Override
  public double getLoadFactor() {
	  // TODO Auto-generated method stub
	  if(this.capacity == 0)
		  return 0;
	  return ((double)this.numKeys / (double)this.capacity);
  }

  /** 
   * gets the capacity
   * @return the capacity
   */
  @Override
  public int getCapacity() {	
	  // TODO Auto-generated method stub
	  return this.capacity;
  }

  /**
   * gets the collision resolution
   * @return the collision resolution chosen
   */
  @Override
  public int getCollisionResolution() {
	  // TODO Auto-generated method stub
	  return 1;
  }

	// TODO: add all unimplemented methods so that the class can compile




		
}
