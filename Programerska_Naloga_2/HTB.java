package Programerska_Naloga_2;

import java.util.Arrays;

public class HTB {
    private int p;
    private int tableSize;
    private int c1;
    private int c2;

    private int currentCollisions;
    private int collisions;
    private boolean collision;

    private HashElement[] hashTable;

    public HTB(int p, int m, int c1, int c2) {
        this.p = p;
        this.tableSize = 0;
        this.c1 = c1;
        this.c2 = c2;
        this.collisions = 0;
        this.collision = false;
        this.currentCollisions = 0;
        this.hashTable = new HashElement[m];
    }

    private int hashFunction(int key) {
        if (collision) {
            return (((key * p) % hashTable.length) + (c1 * currentCollisions)
                    + (c2 * currentCollisions * currentCollisions)) % hashTable.length;
        } else
            return (key * p) % hashTable.length;
    }

    private boolean findInsert(int key) {
        if (find(key)) {
            int hash = hashFunction(key);
            if (hashTable[hash].data == key) {
                collisions -= currentCollisions;
                return true;
            }
            if (currentCollisions == hashTable.length) {
                resize();
            } else {
                collision = true;
                currentCollisions++;
                collisions++;
            }
            findInsert(key);
        }
        return false;
    }

    public void insert(int key) {
        if (tableSize != 0 && findInsert(key)) {
            return;
        }
        int hash = hashFunction(key);

        hashTable[hash] = new HashElement(hash, key);

        tableSize++;
        collision = false;
        currentCollisions = 0;
    }

    public boolean find(int key) {
        int hash = hashFunction(key);

        return Arrays.stream(hashTable)
                .filter(el -> el != null)
                .anyMatch(el -> el.equalsHash(hash));
    }

    public void delete(int key) {
        if (!find(key)) {
            return;
        }
        int hash = hashFunction(key);

        if (hashTable[hash].data == key) {
            hashTable[hash] = null;
        }

    }

    public void printKeys() {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                System.out.println(i + ": " + hashTable[i].data);
            }
        }
    }

    public void printCollisions() {
        System.out.println(collisions);
    }

    private void resize() {
        int tableCapacity = hashTable.length * 2 + 1;

        HashElement[] newTable = new HashElement[tableCapacity];
        var oldTable = hashTable.clone();
        hashTable = newTable;
        tableSize = 0;
        currentCollisions = 0;
        collision = false;

        for (HashElement hashElement : oldTable) {
            if(hashElement != null) {
                insert(hashElement.data);
            }
        }
    }

}

class HashElement {
    public int hash;
    public int data;

    public HashElement(int hash, int data) {
        this.hash = hash;
        this.data = data;
    }

    public boolean equalsHash(int a) {
        return this.hash == a;
    }
}