close all;
clear all;
clc;

step = 0.01;
M = csvread('..\data.csv');
dM = M(2:end,1) - M(1:end-1,1);
start = 0;
for j=1:length(dM)-1
    if (dM(j+1) < 0 && dM(j) > 0)
        if (start == 0) 
            start = j;
        else
            stop = j;
            break
        end
    end
end
period = (stop - start) .* step