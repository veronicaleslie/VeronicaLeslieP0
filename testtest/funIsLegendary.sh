#!/bin/bash

# Associate array to make key-value pairs to assign names of columns indices
# This is so we dont have to memorize the row/index number

declare -a legendary;
declare -A nameDict;
num1=0
num=0
while IFS=, read -r -a array ; do 
    nameDict+=([${array[30]}]=$num)
    temp1=${array[40]:0:1};
    ((num+=1))
    if [[ $temp1 == 1 ]]
    then 
        legendary+=(${array[30]})
        echo ${array[30]} is a legendary
        ((num1+=1))
    fi
done < scripting_pokemon_compendium.csv

echo ${dict[is_legendary]}
echo ${dict[nameDict]}