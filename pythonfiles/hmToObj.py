"""
This module is useful if you want to create an object
out of a hashmap.
For example, if your hashmap looks like this:
{"smth": "that"}, then you just put the word "smth"
in OBJ_STR, and it generates
this.smth = hm.get("smth");

Note that hm can be controlled
"""
import pyperclip


OBJ_STR = R"""
assignedRoom1;
assignedRoom2;
meetingStartTime;
meetingDay;
meetingEndTime;""".strip()


# assume everything is a strong.
# for each line:
# this.code = hm.get("code");


def make_code(key_list_raw: str, hm_name: str) -> str:
    key_list_1 = key_list_raw.split('\n')
    key_list = [x.removesuffix(';') for x in key_list_1]
    lines_new = [f'this.{x} = (String) {hm_name}.get("{x}");' for x in key_list]
    final_str = "\n".join(lines_new)
    pyperclip.copy(final_str)
    print(final_str)


if __name__ == '__main__':
    make_code(OBJ_STR, 'sInfo')
