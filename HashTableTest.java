//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P3a
// Files:           HashTable.java, HashTableTest.java
//
// Course:          CS 400, Spring Term 2019
//
// Author:          Shaan Gagneja
// Email:           sgagneja@wisc.edu
// Lecturer's Name: Debra Deppeler
//
///////////////////////////////////////////////////////////////////////////////

import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*; 
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;



/** TODO: add class header comments here*/
public class HashTableTest{

    // TODO: add other fields that will be used by multiple tests
       
    
    @BeforeAll
    public void setUp() throws Exception {
   
    }

    // TODO: add code that runs after each test method
    @AfterAll
    public void tearDown() throws Exception {
      
    }
    
    /** 
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy 
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test
    public void test000_collision_scheme() {
        HashTableADT htIntegerKey = new HashTable<Integer,String>();
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }
        
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test
    public void test001_IllegalNullKey() {
        try {
            HashTableADT htIntegerKey = new HashTable<Integer,String>();
            htIntegerKey.insert(null, null);
            fail("should not be able to insert null key");
        } 
        catch (IllegalNullKeyException e) { /* expected */ } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    // TODO add your own tests of your implementation
    
    /**
     * This test fails when DS is not empty
     */
    @Test
    public void test00_empty_ds_numKeys() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
        if (dataStructureInstance.numKeys() != 0)
        fail("Test 0: data structure should be empty, with size=0, but size = "+dataStructureInstance.numKeys());
    }
    
    /**
     * This test fails if DS insert function does not work
     */
    @Test
    public void test01_after_insert_one_size_is_one() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
        dataStructureInstance.insert(10, "test");
      
      if(dataStructureInstance.numKeys() != 1) {
        fail("Test 1: The data structure does not create a new object size: " + dataStructureInstance.numKeys() );
      }
      } catch (IllegalNullKeyException | DuplicateKeyException e) {
        // TODO Auto-generated catch block
        fail("Test 1: throws exception");
        e.printStackTrace();
      }
    }
    
    /**
     * This test fails if DS remove function does not work
     */
    @Test
    public void test02_after_insert_one_remove_one_size_is_0() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
        dataStructureInstance.insert(10, "test");
      
      dataStructureInstance.remove(10);
      if(dataStructureInstance.numKeys() != 0) {
        fail("Test 2: Data was not able to remove object size: " + dataStructureInstance.numKeys());
      }
      } catch (IllegalNullKeyException e) {
        // TODO Auto-generated catch block
        fail("Test 2: throws exception");
        e.printStackTrace();
      } catch (DuplicateKeyException e) {
        // TODO Auto-generated catch block
        fail("Test 2: throws exception");
        e.printStackTrace();
      }
    }
    
    /**
     * This test fails if DS insert function does not throw the correct exception
     */
    @Test
    public void test03_duplicate_exception_is_thrown(){
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
        dataStructureInstance.insert(10, "test");
          dataStructureInstance.insert(11, "tes");
          dataStructureInstance.insert(35, "tet");
          dataStructureInstance.insert(78, "tst");
          dataStructureInstance.insert(10, "test");
          fail("Test 3: the data structure did not throw an exception");
      }catch(DuplicateKeyException e) {
        
      }catch(Exception e){
        fail("Test 3: method returned the wrong exception");
      }
    }
    
    /**
     * This test fails if DS remove function returns true if a key is not present in the DS
     */
    @Test
    public void test04_remove_returns_false_when_key_not_present() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
        dataStructureInstance.insert(10, "test");
      
      if(dataStructureInstance.remove(20)) {
        fail("Test 4: Data structure retuned true when it should not");
      }
      } catch (IllegalNullKeyException | DuplicateKeyException e) {
        // TODO Auto-generated catch block
        fail("Test 4: throws exception");
        e.printStackTrace();
      }
    }

    /**
     * This test fails if DS remove function does not remove a key from a DS
     */
    @Test
    public void test05_remove_an_item() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
      dataStructureInstance.insert(10,"test");
      if(!dataStructureInstance.remove(10)) {
        fail("Test 5: Data structure retuned false when it should not");
      }
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      // TODO Auto-generated catch block
      fail("Test 5: throws exception");
      e.printStackTrace();
    }
    }
    
    /**
     * This test fails if DS insert function does not add 100 and remove 100 items
     */
    @Test
    public void test06_adds_many_items_tests_numKeys() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
      for(int i = 0; i < 100; i++) {
        dataStructureInstance.insert(i,"test");
      }
      if(dataStructureInstance.numKeys() != 100) {
        fail("Test 6: Data structure unable to add all the items " + dataStructureInstance.numKeys());
      }
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      // TODO Auto-generated catch block
      fail("Test 4: throws exception");
      e.printStackTrace();
    }
    }
    
    /**
     * This test fails if DS insert function allows a key added when previously removed
     */
    @Test
    public void test07_adds_duplicate_when_removed() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
      dataStructureInstance.insert(10, "test");
      dataStructureInstance.insert(25, "tes");
      dataStructureInstance.insert(15, "tet");
      dataStructureInstance.insert(36, "tst");
      dataStructureInstance.remove(10);
      dataStructureInstance.insert(10, "test");
      }catch(Exception e) {
        fail("Test 7: Data Structure was unable to add a key that was removed before");
      }
    }
    
    /**
     * This test fails if DS insert function can add up to 500 items in DS
     */
    @Test
    public void test08_adds_1000_items_removes_all() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
      for(int i = 0; i < 1000; i++) {
        dataStructureInstance.insert(i, "test");
      }
      
      for(int a = 0; a < 1000; a++) {
        dataStructureInstance.remove(a);
      }
      if(dataStructureInstance.numKeys() != 0) {
        fail("Test 8: unable to add and remove up to 500 items " + dataStructureInstance.numKeys());
      }
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      // TODO Auto-generated catch block
      fail("Test 8: throws exception");
      e.printStackTrace();
    }
    }
    
    /**
     * This test fails if DS insert function does not put an object with the same values in diffrent keys
     */
    @Test
    public void test09_dupplicate_values_are_able_to_be_added() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
      dataStructureInstance.insert(10, "test");
      dataStructureInstance.insert(34, "test");
      dataStructureInstance.insert(78, "test");
      dataStructureInstance.insert(30, "test");
      dataStructureInstance.insert(23, "test");
      
      if(!dataStructureInstance.remove(10) && !dataStructureInstance.remove(23)) {
        fail("Test 9: Data structure is unable to remove item with same value");
      }
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      // TODO Auto-generated catch block
      fail("Test 9: throws exception");
      e.printStackTrace();
    }
    }
    
    /**
     * This test fails if DS contains function does not return the write object with key
     */
    @Test
    public void test10_contains_testing() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
      dataStructureInstance.insert(10, "trest");
      if(!dataStructureInstance.get(10).equals("trest")) {
        fail("Test 10: Data structure was not able to determin if it contains a key");
      }
    } catch (IllegalNullKeyException | DuplicateKeyException | KeyNotFoundException e) {
      // TODO Auto-generated catch block
      fail("Test 10: throws exception " );
      e.printStackTrace();
    }
    }
    
    /**
     * This test fails if DS get function does not the object with the key value
     */
    @Test
    public void test11_get_method_object() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
      dataStructureInstance.insert(10, "test");
      
      if(!dataStructureInstance.get(10).equals("test")) {
        fail("Test 11: Data structure was not able to get a key's object return " + dataStructureInstance.get(10));
      }
    } catch (IllegalNullKeyException | DuplicateKeyException | KeyNotFoundException e) {
      // TODO Auto-generated catch block
      fail("Test 11: throws exception " );
      e.printStackTrace();
    }
    }
    
    /**
     * This test fails if DS get function does not throw the correct exception
     */
    @Test
    public void test12_test_if_get_returns_correct_exception() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
        dataStructureInstance.get(null);
        fail("Test 12: the data structure did not throw an exception");
      }catch(IllegalNullKeyException e) {
        
      }catch(Exception e) {
        fail("Test 12: Data structure does not throw correct exception with null key");
      }
    }
    
    /**
     * This test fails if DS contains function does not throw the correct exception
     */
    @Test
    public void test13_test_if_contains_returns_correct_exception() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
      if(dataStructureInstance.get(null).equals("")) {
        fail("Test 13: the data structure does not return false with null key");
      }
    } catch (IllegalNullKeyException e) {
      // TODO Auto-generated catch block
      
    }catch(Exception e) {
      fail("Test 13: throws exception " );
    }
    }
    
    /**
     * This test fails if DS remove function does not throw the correct exception
     */
    @Test
    public void test14_test_if_remove_returns_correct_exception() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
        dataStructureInstance.remove(null);
        fail("Test 14: the data structure did not throw an exception");
      }catch(IllegalNullKeyException e) {
        
      }catch(Exception e) {
        fail("Test 14: Data structure does not throw correct exception with null key");
      }
    }
    
    /**
     * This test fails if DS insert function does not throw the correct exception
     */
    @Test
    public void test15_test_if_insert_returns_correct_exception() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
        dataStructureInstance.insert(null,"String");
        fail("Test 15: the data structure did not throw an exception");
      }catch(IllegalNullKeyException e) {
        
      }catch(Exception e) {
        fail("Test 15: Data structure does not throw correct exception with null key");
      }
    }
    
    /**
     * This test fails if DS insert function doesn't allow the object to be null
     */
    @Test
    public void test16_test_if_object_can_be_null() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
        dataStructureInstance.insert(10,null);
      }catch(Exception e) {
        fail("Test 16: Data structure does not allow a null object");
      }
    }
    
    /**
     * Inserts with a negative key and gets it. 
     */
    @Test
    public void test17_test_insert_get_neg_key() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
        dataStructureInstance.insert(-1,"hi");
        dataStructureInstance.get(-1);
      }catch(Exception e) {
        fail("Test 17: Does not work correctly;");
      }
    }
    
    /**
     * This test fails if DS insert function and checks if the get function works as well
     */
    @Test
    public void test18_adds_10000_finds_one() {
      HashTableADT dataStructureInstance = new HashTable<Integer,String>();
      try {
      for(int i = 0; i < 10000; i++) {
        dataStructureInstance.insert(i, "test");
      }
      
      String val = (String)dataStructureInstance.get(1000);
      if(!val.equals("test")) {
        fail("Test 8: doesn't work");
      }
    } catch (IllegalNullKeyException | DuplicateKeyException | KeyNotFoundException e) {
      // TODO Auto-generated catch block
      fail("Test 8: throws exception");
      e.printStackTrace();
    }
    }
    
}
