package comp271;

import java.util.ArrayList;

/**
 * Creates a HashMap backed by an ArrayList
 * and HashMap functions
 * @author revised to open addressing by Katherine Wade
 *
 * @param <T> Generic type for value of entries
 */
public class HashMap271<T> {
	
    private class Entry {
        String key;
        T value;

        public Entry(String k, T v) {
            this.key = k;
            this.value = v;
        }
    }

    private int size = 2;
    private int entries = 0;
    private double loadFactor;
    private final double THRESHOLD = 0.7;
    private ArrayList<Entry> map;
    private boolean isResizing = false;

    public HashMap271() {
        this.map = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
        	this.map.add(null);
        }
    }

    public void put(String key, T val) {
        this.entries++;  // increments entries when another is added, for calculating loadFactor
        calculateLoadFactor();  // determined by entries to size
        if (loadFactor > THRESHOLD && !this.isResizing) {
            this.entries = 1;  // before resize entries set to 1 bc will be incremented as put() is called in rehashing
            resizeAndRehash();
        }

        int hash = this.getHashCode(key);  // index of the bucket this key is mapped to
        Entry entry = this.map.get(hash);  // identifies an entry reference associated with this hash index
        
    	while (entry != null && entry.key != null) {  // if the entry is not empty (since start (entry=null) or empty after removal (entry.key=null))
    		++hash;  // increment hash index to check whether next bucket is available
    		if (hash == this.size) {  // if hash index has reached the end of ArrayList, start back at 0
    			hash = 0;
    		}
    		entry = this.map.get(hash);  // assign entry reference to current hash index before re-entering while loop
    	}
    	this.map.set(hash, new Entry(key, val));  // upon exiting the while loop, we have found an available index, so assign the entry
    }

    private void resizeAndRehash() {
        // create a new map and update sizes and load factor
        this.size *= 2;  // double the size
        ArrayList<Entry> temp = this.map;  // create a new variable called temp to point to the current ArrayList object
        this.map = new ArrayList<>(size);  // this.map points to new ArrayList of updated(doubled) size
        for (int i = 0; i < size; ++i) {  // add null values as place holders in new ArrayList of updated size
        	this.map.add(null);
        }

        this.isResizing = true;  // isResizing true will prevent resizeAndRehash from being called during next steps

        // now rehash existing items and put them in the new ArrayList
        // iterate over all elements in the old ArrayList
        for(Entry entry : temp) {  // for each entry in temp ArrayList
            if (entry != null && entry.key != null) {  // if entry is not null, call put() to transfer entry 
                this.put(entry.key, entry.value);      // (will be rehashed in put() because the modulo changes when size of ArrayList changes)
            }
        }
        this.isResizing = false;  // rehashing is complete, so next time put() is called, it will be able to call resizeAndRehash if needed
    }

    public T get(String key) {
        int hash = this.getHashCode(key);  // determines hash index of the key
        Entry entry = this.map.get(hash);  // identifies an entry reference associated with this hash index
        if (entry == null) {  // if entry == null, this index has been empty since start, so no need to continue searching
        	return null;
        }
        while (entry.key == null || !entry.key.equals(key)) {  // entry.key == null means this entry is empty after removal, 
        	++hash;											   // so the value could have been placed in another location at time of put()
        	if (hash == this.size) {						   // so we use the same steps described in put() to continue checking buckets
    			hash = 0;									   // see lines 60 - 64 correspond to lines 97 - 101
    		}
        	entry = this.map.get(hash);
        	if (entry == null) return null;  // if entry == null, is empty since start, so return null and stop searching
        }
        return entry.value;
      
    }

    public void remove(String key) {
        int hash = this.getHashCode(key);  // determines hash index of the key
        Entry entry = this.map.get(hash);  // identifies an entry reference associated with this hash index
        if (entry == null) {  // if entry == null, this index has been empty since start, so no need to continue searching
            return;
        }
        while (!entry.key.equals(key)) {  // while entry key is not equal to key we are searching for in order to remove
        	++hash;						  // we use steps described above (lines 60 - 64 and 97 - 101) to increment hash index
        	if (hash == this.size) {	  // and continue at index zero if we reach the end of the list without finding the key or
    			hash = 0;				  // a hard stop at an empty-since-start
    		}
        	entry = this.map.get(hash);
        	if (entry == null) return;  // entry == null is empty-since-start, which means we can stop searching
        }
        entry.key = null;  // if we have made it past the while loop to this line, we have found the matching entry and remove it by assigning a null value to the key to indicate empty-after-removal
        this.entries--;  // account for removal of entry in entries total
        calculateLoadFactor();  // calculate here because number of entries has changed
    }

    private void calculateLoadFactor() {
        this.loadFactor = (double) this.entries / (double) this.size;
    }

    public int getHashCode(String val) {
        int charSum = 5381;
        for(char c : val.toCharArray()) {
            charSum += (int) c;
        }

        return (charSum * 33) % this.size;
    }
}