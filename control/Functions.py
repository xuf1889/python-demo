
def ask_ok(prompt , retries = 4 , reminder = 'Please try again!'):
    while True:
        ok = input(prompt)
        if ok in ('y' , 'yes'):
            return True
        if ok in ('n' , 'no'):
            return False
        retries = retries - 1
        if retries < 0:
            raise ValueError('invalid user response')
        print(reminder )

#print(ask_ok('y'))
print(ask_ok('y' , 1))