find . -type f -name "*" -exec sed -i'' -e 's/lbryquest/lbryquest/g' {} +
find . -type f -name "*" -exec sed -i'' -e 's/LBRYQuest/LBRYQuest/g' {} +
find . -type f -name "*" -exec sed -i'' -e 's/LBRYquest/LBRYquest/g' {} +
find . -type f -name "*" -exec sed -i'' -e 's/lbryQuest/lbryQuest/g' {} +
find . -type f -name "*" -exec sed -i'' -e 's/LBRYQUEST/LBRYQUEST/g' {} +
shopt -s globstar
find . * | rename 's/lbryquest/lbryquest/g'
shopt -s globstar
find . * | rename 's/lbryquest/lbryquest/g'
shopt -s globstar
find . * | rename 's/LBRYQuest/LBRYQuest/g'
shopt -s globstar
find . * | rename 's/LBRYquest/LBRYquest/g'
shopt -s globstar
find . * | rename 's/lbryQuest/lbryQuest/g'
shopt -s globstar
find . * | rename 's/LBRYQUEST/LBRYQUEST/g'
