def main(table):
    new_table = []
    for i in table:
        if i != [None, None, None, None]:
            new_table.append([value for value in i if value])

    dup_free = []
    for x in new_table:
        if x not in dup_free:
            dup_free.append(x)

    for i in dup_free:
        i[0] = i[0].replace('@', '[at]')
        temp = i[0].split(":")
        i[1] = str('{:.3f}'.format(float(i[1])))
        if temp[1] == 'true':
            i.append('1')
        else:
            i.append('0')
        i[0] = temp[0]

    ans = list(map(list, zip(*dup_free)))
    return list(reversed(ans))


table = [[None, 'liketin88@mail.ru:true', None, '0.46'],
         [None, None, None, None],
         [None, 'detisman60@yandex.ru:false', None, '0.31'],
         [None, 'luselij46@gmail.com:false', None, '0.27'],
         [None, 'luselij46@gmail.com:false', None, '0.27'],
         [None, 'nosurak75@rambler.ru:false', None, '0.78']]
print(main(table))
