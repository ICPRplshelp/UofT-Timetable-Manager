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
# UT = f"https://timetable.iit.artsci.utoronto.ca/api/{SESSION}/courses?org=&section="


def main(ses: str) -> None:
    print('running the timetable scrapper. this may take up to 20 seconds.')
    ut = f"https://timetable.iit.artsci.utoronto.ca/api/{ses}/courses?org=&section="
    # DO NOT CHANGE THIS
    all_des = ['F', 'S', 'Y']
    master_dictionary = {}
    fn = "20229"
    for des in all_des:
        ut_link = ut + des
        cl = requests.get(ut_link)
        cl_json = cl.json()
        print(f'obtained request for {des}')
        master_dictionary.update(cl_json)

    # print(master_dictionary)
    with open(f'../src/courses{ses}.json', 'w', encoding='UTF-8') as f:
        json_text = json.dumps(master_dictionary, ensure_ascii=False, indent=0,
                               separators=(',', ':'))
        print(json_text)
        f.write(json_text)


if __name__ == '__main__':
    sessions = ['20229', '20225', '20219', '20215',
                '20209', '20205', '20199', '20195']
    for sess in sessions:
        main(sess)
