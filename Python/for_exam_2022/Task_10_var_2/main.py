def main(x):
    new_table = []
    for i in x:
        if i != [None, None, None, None]:
            new_table.append(i)
    for i in new_table:
        i[3] = i[3].replace('-', '/')
        firstColumSplit = i[0].split('#')
        i[0] = round(float(firstColumSplit[0]), 3)
        i[2] = firstColumSplit[1][:13] + firstColumSplit[1][14:]
        if i[1] == 'Выполнено':
            i[1] = 'true'
        else:
            i[1] = 'false'
    return new_table

print(main([['0.7695#+7 994 773-26-99', 'Выполнено', 'Выполнено', '1999-09-11'], ['0.0056#+7 701 035-12-82', 'Выполнено', 'Выполнено', '2003-07-21'], [None, None, None, None], [None, None, None, None], ['0.0807#+7 279 426-79-21', 'Не выполнено', 'Не выполнено', '2002-11-08']]))