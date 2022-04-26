#!/bin/bash

# Associate array to make key-value pairs to assign names of columns indices
# This is so we dont have to memorize the row/index number

declare -a legendary;
declare -a healthList;
declare -a attackList;
declare -a nameDict;
num1=0
num=0
zeroValue=0

# Set lists attack, health, etc

while IFS=, read -r -a array ; do 
    nameDict+=([${array[30]}])
    healthList+=([${array[28]}])
    attackList+=([${array[19]}])
    temp1=${array[40]:0:1};
    ((num+=1))
    if [[ $temp1 == 1 ]]
    then 
        legendary+=(${array[30]})
        ((num1+=1))
    fi
done < scripting_pokemon_compendium.csv


# while true for gameplay
loop1=true
loop2=true
while [ $loop1 == true ]
do
    #reset loop 
    loop2=true
    #set random number
    r1=$((5 + $RANDOM % 10))
    r2=$((5 + $RANDOM % 10))
    random=$(( r1*4 ))
    random2=$(( r1*r2*4 ))
    tempHP1=${healthList[random]}
    pokeHP1="${tempHP1:1:-1}"
    tempHP2=${healthList[random]}
    pokeHP2="${tempHP2:1:-1}"

    # Output
    echo A ${nameDict[random]} is about to fight a ${nameDict[random2]}
    echo "..."
    sleep 3
    echo The attack of the ${nameDict[random]} is ${attackList[random]} and the health is ${healthList[random]}
    echo "..."
    sleep 3
    echo The attack of the ${nameDict[random2]} is ${attackList[random2]} and the health is ${healthList[random2]}
    
    while [ $loop2 == true ]
    do
    temp=(${attackList[random2]})
    temp2="${temp:1:-1}"
    pokeHP1=$(( pokeHP1-temp2 ))
    echo Pokemon ${nameDict[random2]} attacks ${nameDict[random]} ":" ${nameDict[random]} new health is $pokeHP1
    
    temp=(${attackList[random]})
    temp2="${temp:1:-1}"
    pokeHP2=$(( pokeHP2-temp2 ))
    echo Pokemon ${nameDict[random]} attacks ${nameDict[random2]} ":" ${nameDict[random2]} new health is $pokeHP2

        if (( $pokeHP1 < 0 )); then
            echo Pokemon ${nameDict[random]} has DIED!
            loop2=false
            sleep 2
        fi

        if (( $pokeHP2 < 0 )); then
            echo Pokemon ${nameDict[random2]} has DIED!
            loop2=false
            sleep 2
        fi
    sleep .1
    done

done