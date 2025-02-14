#!/usr/bin/env bash

# Generate a random commit message
messages=("Update code" "Refactor" "Fix bug" "Add new feature" "Improve performance" "Optimize code" "Update documentation" "New Microservice" "Cleanup files" "Enhance functionality" "Resolve issues")
RANDOM_MESSAGE=${messages[$RANDOM % ${#messages[@]}]}

git pull
git add .
git commit -m "$RANDOM_MESSAGE"
git push
