FROM python:3.8

# Setup env
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8
ENV PYTHONFAULTHANDLER 1

RUN pip install numpy cython sklearn lightgbm catboost numba
RUN pip install torch==1.7.0+cpu -f https://download.pytorch.org/whl/torch_stable.html

COPY . /project
WORKDIR /project
