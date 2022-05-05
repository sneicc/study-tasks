import re


def main(arg):
    start_end = '\\|\\|*'
    key_regex = '\\s*<<\\n*\\s*glob\\s'
    assign = '\\s*<=\\s*list\\(\\s*'
    start_list = "`\\w+\\s"
    start_list_bad = "`\\w+\\)"
    start_list_ultra_bad = "`\\w+\\)\\s"
    end_of_key = '\\)?\\s*>>,\\s*'
    ans = {}
    arg = re.sub(start_end, '', arg)
    while len(arg) != 0:
        list_of_values = []
        result = re.match(key_regex, arg)
        arg = arg[result.end():]
        result = re.search(assign, arg)
        key = arg[:result.start()]
        arg = arg[result.end():]  # нахождение ключа
        while (arg[0] != ")") & (arg[0] != ">"):
            result = re.match(start_list, arg)
            if result is not None:
                list_of_values.append(arg[result.start()+1:result.end()-1])
                arg = arg[result.end():]
            else:
                result = re.match(start_list_ultra_bad, arg)
                if result is not None:
                    temp = arg[result.start() + 1:result.end() - 2]
                    list_of_values.append(temp)
                    arg = arg[result.end():]
                else:
                    result = re.match(start_list_bad, arg)
                    temp = arg[result.start() + 1:result.end() - 1]
                    list_of_values.append(temp)
                    arg = arg[result.end():]
        ans[key] = list_of_values
        result = re.match(end_of_key, arg)
        arg = arg[result.end():]
    return ans


print(main('|| <<glob onatus_226 <=list(`ararri_696 `inleri_567) >>,<< glob\nditees_483 <= list( `isce `biiser_516) >>, << glob rabe<= list(\n`tiin_49 `xeusan_218 `esce_489 `geus ) >>, << glob xexe <=list(\n`onater `cein `zaisma_357 `cexele ) >>, ||'))
