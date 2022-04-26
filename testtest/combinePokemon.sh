#!/bin/bash

file=$(ls data_backup/*csv | head -1)

cat $file | head -1 > scripting_poekmon_compendium.csv

for i in $(ls data_backup/*csv); do 
	cat $i | grep -v "abilities" ; 
done >> scripting_pokemon_compendium.csv