freq = 0
seen = set()

with open('input', 'r') as f:
    changes = [int(l) for l in f.readlines()]

i = 0
while True:
    freq += changes[i % len(changes)]
    if freq in seen:
        print(freq)
        break
    else:
        seen.add(freq)
    i += 1
