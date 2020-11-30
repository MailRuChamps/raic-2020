set -ex

if [ "$1" != "base" ]; then
    if [[ `ls -1 /src/ | wc -l` -eq 1 ]]; then
        cp -f /src/my_strategy.go my_strategy.go
    else
        rm -rf ./*
        cp -rf /src/* ./
    fi
fi

go build -o aicup2020
cp aicup2020 /output/