#!/bin/bash

file=$1
type=$2
pokemon=$3

function readDatabase {
    backupFileLink=scripting_pokemon_compendium.csv
    if [ !$file ]
    then
        cat $backupFileLink
    else 
        cat $file 
    fi
}

function searchForType {
    readDatabase | grep "$type"
}

function selectAColumns {
    readDatabase | cut -d ',' -f 1
}

function selectColumns {
    readDatabase | grep "$type"
}

searchForType