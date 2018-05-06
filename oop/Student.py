class Student(object):
    def __init__(self , name , age):
        self.__name = name
        self.__age = age

    def print_info(self):
        print('name = ' , self.__name , ',age = ' , self.__age)


"""stu = Student('张三' , 16)
print(stu)
stu.print_info();
"""