
import math
from functools import reduce

ids = open('input', 'r').readlines()

print(
    list(
        map(
            lambda x: reduce(
                          lambda x,y: x + y,
                          map(lambda x: x[0], filter(lambda x: x[0] == x[1], zip(x[1], x[2]))),
                          ''
                      ),
            filter(
                lambda x: x[0],
                map(lambda z: (sum([int(abs(z1 - z2) != 0) for (z1, z2) in z[0]]) == 1, z[1], z[2]),
                    map(
                        lambda x: (zip([ord(c) for c in x[0]], [ord(c) for c in x[1]]), x[2], x[3]),
                        filter(
                            lambda x: x[0] != x[1],
                            [(a.strip(), b.strip(), a.strip(), b.strip()) for a in ids for b in ids if a < b]
                        )
                    )
                )
            )    
        )
    )[0]
)
