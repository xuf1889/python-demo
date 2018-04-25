words = ['cat' , 'window' , 'defenestrate']
for w in words:
    print(w , len(w))
print('-----------------------------------')

for w in words[:]:
    if len(w) > 6:
        words.insert(0 , w)
print(words)
print('-----------------------------------')

for i in range(5):
    print(i)
print('-----------------------------------')

print(range(5 , 10))
list(range(0, 10 ,3))
range(-10 , -100 , -30)

def fib(n):
    a, b  = 0 , 1
    while a < n:
        print(a , end=' ')
        a , b  = b , a + b
    print()
fib(2000)
