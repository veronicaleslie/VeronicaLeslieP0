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
    readDatabase | grep "$type"
}

function generateTeamWith100HP {
    echo Implement
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
    readDatabase | grep "$pokemon"
}

findPokemon