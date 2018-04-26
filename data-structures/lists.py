import control
list = []

list.append('first')
list.insert(2 , 'second')
list.append('four')
list.insert(1 , 'thrid')
list.insert(0 , 'one')
list.insert(7 , 'seven')
print(list)
print(list[4])
print(list.pop(3))
del list[0]
print('second' in list)

basket = {'apple','orange','apple','pear','banana'}
print(basket)
print('apple' in basket)

en = enumerate(['tic','tac','toe'])
print('tic' in en)
