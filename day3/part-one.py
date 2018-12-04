

from pprint import pprint
import re

input = list(map(lambda m: (m[1], m[2], m[3], m[4], m[5]), [re.match('#(\d+) @ (\d+),(\d+): (\d+)x(\d+)', a.strip()) for a in open('input', 'r').readlines()]))


pprint(input[0:5])
