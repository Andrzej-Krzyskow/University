#include <stdio.h>
#include <stdlib.h>

int main()
{
    int totals[4][5] = {0};
    int totalColumns[4] = {0};
    FILE *data = fopen("D:\\Obrazy & Filmy\\vids\\PWr\\Sem3\\1C++\\EPT\\Labs1\\slips.txt", "r");

    char line[10];
    int counter = 0;
    int salesman = 0;
    int product = 0;

    while (fgets(line, sizeof(line), data))
    {

        int number;
        sscanf(line, "%d", &number);
        counter++;

        switch (counter)
        {
        case 1:
            salesman = number - 1;
            break;
        case 2:
            product = number - 1;
            break;
        case 3:
            counter = 0;
            totals[salesman][product] += number;
            break;

        default:
            break;
        }
    }

    for (int i = 0; i < 5; i++)
    {
        int totalRow = 0;
        for (int j = 0; j < 4; j++)
        {
            printf("%4d ", totals[j][i]);
            totalRow += totals[j][i];
            totalColumns[j] += totals[j][i];
        }
        printf("\t| %4d\n", totalRow);
    }

    for (int i = 0; i < 12; i++)
    {
        printf("--");
    }
    printf("\n");
    for (int i = 0; i < 4; i++)
    {
        printf("%4d ", totalColumns[i]);
    }

    return 0;
}