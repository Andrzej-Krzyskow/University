#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    FILE *file;
    srand(time(NULL));

    int DAYS = 30;
    int MAX_VALUE_PER_DAY = 200;

    file = fopen("slips.txt","w");

    for(int i=0; i<DAYS; i++){
        for(int salesmanID=1; salesmanID<5; salesmanID++){

            int slips = rand()%6;

            for(; slips>0; slips--){
                fprintf(file, "%d\n", salesmanID);
                fprintf(file, "%d\n", rand()%5+1);
                fprintf(file, "%d\n", rand()%MAX_VALUE_PER_DAY+1);
            }
        }
    }

    fclose(file);

    return 0;
}