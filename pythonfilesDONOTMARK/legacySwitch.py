"""Utility to revert enhanced switch-case blocks
to legacy switch-case blocks, in case
the project needs to be reverted to Java 11."""


import pyperclip


INPUT = R"""
            case BAN -> {  
                String userToBan = presenter.enterUsername();  
                Date unbanDate = new Date();  
                try {  
                    unbanDate = presenter.enterDate();  
                } catch (ParseException e) {  
                    // Unsure if this is supposed to be printed to screen; put into presenter for now.  
                    // System.out.println("Failed to parse string to date");  
                    presenter.parseFailure();  
                }  
                boolean banCheck = adminAccountManager.banUser(userToBan, unbanDate);  
                presenter.banUserConfirm(banCheck, userToBan);  
  
                // banUser(userToBan, unbanDate);  
            }  
  
            case DELETE -> {  
                String userToDelete = presenter.enterUsername();  
                boolean delUserSuccess = adminAccountManager.deleteUser(userToDelete);  
                presenter.deleteUserConfirm(delUserSuccess, userToDelete);  
  
                // deleteUser(userToDelete);  
            }  
  
            case NEW -> {  
                String[] inputs = presenter.enterCredentials();  
                boolean isCreated = adminAccountManager.createNewAdminUser(inputs[0], inputs[1]);  
                presenter.createNewAdminConfirm(isCreated, inputs[0]);  
            }  
  
            case PROMOTE -> {  
                String userToPromote = presenter.enterUsername();  
                presenter.promoteUserConfirm(adminAccountManager.addPermission(userToPromote, "admin"), userToPromote);  
                // promoteUserToAdmin(userToPromote);  
            }  
            case BACK -> curState = LoggedInState.STANDARD;default -> {  
                presenter.genericFailedAction("invalid");  
                return false;  
            }  
""".strip()


def repair(text: str) -> str:
    """Revert text, which uses
    Java's enhanced Switch statements, to
    Java's legacy switch statements.

    The default block must be placed the last and may
    only be the time the word appears in the input string.

    The text passed in must explicitly be inside
    and may not include the switch statement.

    The text may not have any braces or comments that are included
    within string literals.
    """
    # text = remove_java_comments(text)
    # print(text)
    default_loc = text.find('default')
    default_text = text[default_loc + len('default'):]
    default_text_2 = 'default ' + repair_each_case(default_text)
    text = text[:default_loc]
    cases1 = text.split('case')
    cases2 = [x.strip() for x in cases1 if x.strip() != '']
    cases3 = ['case ' + repair_each_case(x) for x in cases2]
    return '\n'.join(cases3) + '\n' + default_text_2


def repair_each_case(text: str) -> str:
    """Repair each case.
    An example of what would be
    passed in:

    404 -> do.this();
    405 -> {do.this();}
    """
    tab = '    '
    arrow_location = text.find('->')
    opening_brace_location = text.find('{')
    if opening_brace_location == -1:
        # No opening brace?
        text_insert = text[arrow_location + 2:].strip()
        br = 'break;' if 'return' not in text_insert else ''
        return text[:arrow_location - 1] + ': \n' + tab + text_insert + '\n' + tab + br
    else:
        closing_brace_location = find_next_closing_brace(text, opening_brace_location)
        switch_contents = text[opening_brace_location + 1:closing_brace_location]
        text_insert = switch_contents.strip()
        br = 'break;' if 'return' not in text_insert else ''
        return text[:arrow_location - 1] + ': \n' + tab + text_insert + '\n' + tab + br


def find_next_closing_brace(text: str, index: int) -> int:
    """Find the position of the next closing brace.
    :param text: the text to pass in
    :param index: the index the opening brace is at
    :return: uhhh
    """
    assert text[index] == '{'
    layer = 1
    for i in range(index + 1, len(text)):
        if text[i] == '{':
            layer += 1
        if text[i] == '}':
            layer -= 1
        if layer == 0:
            return i
    raise ValueError('Could not find a closing brace')


def remove_java_comments(text: str) -> str:
    """Remove Java comments from the text.

    Preconditions:
        - no '//' in string literals

    :param text: the text to pass in
    :return: the text without comments
    """
    return '\n'.join([x[:x.find('//')] for x in text.split('\n') if not x.startswith('//')])


if __name__ == '__main__':
    ri = repair(INPUT)
    print(ri)
    pyperclip.copy(ri)
