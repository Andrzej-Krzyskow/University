import sys
from read_log import read_log
from get_successful_reads import get_successful_reads
from get_failed_reads import get_failed_reads
from get_entries_by_code import get_entries_by_code
from get_entries_by_extension import get_entries_success_by_extension
from print_entries import print_entries


def run(file_name):
    list_of_logs = read_log(file_name)
    list_of_success = get_successful_reads(list_of_logs)
    list_of_failed = get_failed_reads(list_of_logs)
    list_of_code_xxx = get_entries_by_code(list_of_logs, 304)
    list_ofc_success_by_extension = get_entries_success_by_extension(list_of_logs, 'html')

    # print_entries(list_ofc_success_by_extension)
    # f = open('list.txt', 'w')
    # for x in list_ofc_success_by_extension:
    #     f.write(str(x) + '\n')
    # f.close()


if __name__ == '__main__':
    if len(sys.argv) == 1:
        run('nasa')
    else:
        run(sys.argv[1])
else:
    exit()
