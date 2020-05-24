# MatchingEngine

Steps to run
-------------
1. Import the project in eclipse
2. Build the project
3. Run TestAppRuntime.java file to test the  engine

![Data structure](https://i.ibb.co/7kcFKVb/IMG-20200524-150245.jpg)

Extensibility with existing rules: reordering/ adding/ deleting
-------------
All types, GenderDataset, AgeDataset, InterestDataset inherit from RuntimeDataset and can be reorrderred to change the rule sequuences.
The only cahnges required will be:
1. Establish a link structure for the rule sequence: Age -> Gender -> Interests
2. in addRuntimeEntityToDataset() methods, create the map based on this link.

With this, we can abstaract out runtime ddata creation further to take any sequence of data in age, gender, interests.


Extensibility with new rules
-------------
Adding a new rule simply required adding its implementation and plugging it into the  terminal RuntimeDataset type. That will automatically create the next level with new rules.


Further optimizations
-------------
We can optimize this further by:
1. Ordering interest dataset during creation
2. Maintaining a superset of interests. Interests will always a limited set  of words.
3. In the Interest dataset queryDataset() method, which is currently  O(n*k*k); n = num users, k = num interests, we  can create a set of interests and do a linear scan over this to get O(n +  (k*n)) = O(n*k) time. That will be an improvement over O(n*k^2).
