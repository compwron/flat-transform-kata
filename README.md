This is a sample of the frequently-repeating pattern which I call a "flat transform kata" i.e. making a flat data structure into a hierarchical data structure.

First round rules:
Given this data (either in csv file or database, whatever) transform the flat model to the hierarchical model. 

```
Starter data:
fooId1, fooDescription1, barId1, barDescription1
fooId1, fooDescription1, barId2, barDescription2
fooId2, fooDescription2, barId3, barDescription3

End data:
- foo1
-- bar1
-- bar2
- foo2
-- bar3

Second round (advanced) rules:
Validate/cleanse data on import such that
- There can be no duplicated "bar" (i.e. foo1 and foo2 cannot both have a bar1, and foo1 cannot have two bar2)
- Some data is invalid:
-- i.e. "foo3" is invalid and no flat model with foo3 can be persisted to the end model
-- any bar with description field "stuff" is invalid and cannot be persisted (but this does not invalidate the foo unless the invalid bar is the only bar in foo)
```

I assume that when solving in Java, the most sane way to solve this involves guava transforms. 

