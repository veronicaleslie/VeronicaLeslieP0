#!/bin/bash

file=$1
type=$2
pokemon=$3

allArguments=$@

echo $allArguments

function addition {
    
    num=0
    for i in $allArguments
    do
        ((num+=$i))
    done
    echo $num
}

function readDatabase {
    if [ !$file ]
    then
        file=scripting_pokemon_compendium.csv
    fi

    cat $file
}

function selectAColumnAndType {
    searchForType | cut -d ',' -f 31
}

function searchForType {
    type=water
    readDatabase | grep "$type"
}

    function getArray {
    while IFS=, read -r -a array ; do
    genArray+=([${array[$type]}])
    done < $file
    echo $genArray[1]
}
function generateTeamWith100HP {
    while IFS=, read -r -a array ; do
    if (( ${array[28]} <= 100 ))
    then
        echo ${array[30]} ${array[28]}
    fi
    done < scripting_pokemon_compendium.csv
}


function selectAColumn {
    columnNum=$1
    readDatabase | cut -d ',' -f $1
}


function selectColumns {
    readDatabase | cut -d ',' -f 1,2,30-41 
}

function findIndex {
    echo Implement
}

function findPokemon {
    readDatabase | grep "$Pikachu" | cut -d ',' -f 1,20-30
}
generateTeamWith100HP

