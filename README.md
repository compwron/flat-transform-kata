This is a sample of the frequently-repeating pattern which I call a "flat transform kata" i.e. making a flat data structure into a hierarchical data structure.

If you want to do this kata:

Starting data structure:
betaID, betaName, gammaId, gammaName
1 a 100 aa
2 b 200 bb
2 c 300 cc

Expansion 1:
Exclude data with "OTHER" or "XXX" in any description

Expansion 2:
There can be no duplicate gammas (so alpha1 and alpha2 cannot have the same beta, and two betas cannot have the same gamma)

I assume that when solving in Java, the most sane way to solve this involves guava transforms. 

