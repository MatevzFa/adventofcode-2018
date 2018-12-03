
from collections import Counter
from pprint import pprint

from functools import reduce

ids = open('input', 'r').readlines()

print(
    reduce(lambda x, y: x * y,
           reduce(lambda cur, acc: (acc[0] + cur[0], acc[1] + cur[1]),
                  map(lambda x: (int(2 in x), int(3 in x)),
                       [list(map(lambda x: x[1], Counter(id.strip()).items())) for id in ids]),
                  (0, 0)),
           1)
)
