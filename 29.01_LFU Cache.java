public class LFUCache {
    final int capacity;
    int minFreq, size;
    HashMap<Integer, Node> cache;
    HashMap<Integer, doubleLinkedList> freqMap; 
    // freaqmap, cache, size, minFreq, capacity.
    public LFUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        minFreq = 0;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }
    
    public int get(int key){
        Node node = cache.get(key);
        if(node == null){
            return -1;
        }

        updateNode(node);
        return node.val;
    }
    // delete the node freq inside its list and increment then add it to the new list with its freq list
    public void updateNode(Node node){
        int freq = node.freq;
        doubleLinkedList list = freqMap.get(freq);
        list.removeNode(node);
        if(freq == minFreq && list.size == 0){
            minFreq++;
        }
        // update the new list.
        node.freq++;
        doubleLinkedList newList = freqMap.getOrDefault(node.freq, new doubleLinkedList());
        newList.addNodeToHead(node);
        freqMap.put(node.freq, newList);

    }
    
    // if the node exists,update the freq count, else create a new node.
    public void put(int key, int value) {
        if(capacity == 0){
            return;
        }

        if(cache.containsKey(key)){
            Node node = cache.get(key);
            node.val = value;
            updateNode(node);
        }else{
            size++;
            if(size > capacity){
                doubleLinkedList leastList = freqMap.get(minFreq);
                Node deleteNode = leastList.removeTail();
                cache.remove(deleteNode.key);
                size--;

            }
            minFreq = 1;
            Node newNode = new Node(key, value);
            doubleLinkedList newList = freqMap.getOrDefault(1, new doubleLinkedList());
            newList.addNodeToHead(newNode);
            freqMap.put(1, newList);
            cache.put(key, newNode);
        }
    }
    class Node{
        int key, val, freq;
        Node next, prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
            freq = 1;
            next = null;
            prev = null;
        }
    }

    class doubleLinkedList{
        Node head, tail;
        int size;
        public doubleLinkedList(){
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }
    
    // head -> <-x-> y z.... Tail
    public void addNodeToHead(Node node){
        Node oldHead = head.next;
        node.next = oldHead;
        node.prev = head;
        head.next = node;
        oldHead.prev = node;
        size++;
    }
    // head ...x -> <- y -> <- z... tail
    public void removeNode(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
    }

    public Node removeTail(){
        if(size > 0){
            Node node = tail.prev;
            removeNode(node);
            return node;
        }
        return null;
    }
}
}