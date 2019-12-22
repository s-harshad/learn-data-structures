
#### Path Compression Variants

_*Regular*_ Path Compression
```java
int find(int p) {

    int root = p;

    //find the root of the given node
    while (root != id[root])
        root = id[root];

    // path compression
    // start from given node, update each node's parent to be root
    while(p != root) {
        int parent = id[p];
        id[p] = root;
        p = parent;
    }

    return root;
}
``` 

Path Compression by halving (same as path splitting except that it’s done for every other node)
```java
int find(int p) {
    while (p != parent[p]) {
        // path compression by halving
        parent[p] = parent[parent[p]];
        p = parent[p];
    }
    return p;
}
```

Path Compression by Path Splitting (set the parent pointer of each node on the path to its grandparent)
```java
int find(int p) {
    while (p != parent[p]) {
        int next = parent[p];
        // path splitting
        parent[p] = parent[next];  
        p = next;
    }
    return p;
}
```