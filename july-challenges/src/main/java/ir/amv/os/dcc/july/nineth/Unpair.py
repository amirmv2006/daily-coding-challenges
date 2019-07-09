def cons(a, b):
    def pair(f):
        return f(a, b)
    return pair


def car(cons):
    def unpairFirst(a, b):
        return a
    return cons(unpairFirst)

def cdr(cons):
    def unpairLatter(a, b):
        return b
    return cons(unpairLatter)


print(car(cons(3, 4)))
print(cdr(cons(3, 4)))