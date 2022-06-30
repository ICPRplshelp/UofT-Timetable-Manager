"""This is the Python code used to scrap courses from the UofT timetable.
Run this any time to update coursesMASTER.json.
DO NOT MARK THIS FILE.

In the future, this code may need to be ported over to Java.
However, I would like to NOT overload UofT's servers, because
there are some tests that would otherwise run API requests multiple
times.
"""

import requests
import json

SESSION = "20229"
UT = f"https://timetable.iit.artsci.utoronto.ca/api/{SESSION}/courses?org=&section="

if __name__ == '__main__':
    print('running the timetable scrapper. this may take up to 20 seconds.')
    # DO NOT CHANGE THIS
    all_des = ['F', 'S', 'Y']
    master_dictionary = {}
    fn = "20229"
    for des in all_des:
        ut_link = UT + des
        cl = requests.get(ut_link)
        cl_json = cl.json()
        print(f'obtained request for {des}')
        master_dictionary.update(cl_json)

    # print(master_dictionary)
    with open('../src/coursesMASTER.json', 'w', encoding='UTF-8') as f:
        json_text = json.dumps(master_dictionary, ensure_ascii=False, indent=0,
                               separators=(',', ':'))
        print(json_text)
        f.write(json_text)
