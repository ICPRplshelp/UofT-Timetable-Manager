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

                        "postId": null,
                        "postCode": null,
                        "postName": null,
                        "subjectId": "1",
                        "subjectCode": "*",
                        "subjectName": "",
                        "designationId": "1",
                        "designationCode": "*",
                        "designationName": "",
                        "yearOfStudy": "1",
                        "typeOfProgramId": null,
                        "typeOfProgramCode": null,
                        "typeOfProgramName": null,
                        "primaryOrgId": "6",
                        "primaryOrgCode": "ARTSC",
                        "primaryOrgName": "Faculty of Arts and Science",
                        "secondaryOrgId": "1",
                        "secondaryOrgCode": "*",
                        "secondaryOrgName": "",
                        "assocOrgId": "1",
                        "assocOrgCode": "*",
                        "assocOrgName": "",
                        "adminOrgId": "1",
                        "adminOrgCode": "*",
                        "adminOrgName": ""


""".strip()


# assume everything is a strong.
# for each line:
# this.code = hm.get("code");


def make_code(key_list_raw: str, hm_name: str) -> str:
    key_list_1 = key_list_raw.split('\n')
    key_list = [x.removesuffix(';').strip().removeprefix('"').split('"')[0] for x in key_list_1]
    lines_new = [f'this.{x} = (String) {hm_name}.get("{x.strip()}");' for x in key_list]
    final_str = "\n".join(lines_new)
    pyperclip.copy(final_str)
    print(final_str)
    for kl in key_list:
        print(kl.strip())
    return '\n'.join(kl)


if __name__ == '__main__':
    make_code(OBJ_STR, 'controls')
