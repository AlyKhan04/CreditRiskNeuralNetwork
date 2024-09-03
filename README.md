## Overview
This project is a Neural Network built from scratch in Java to evaluate a Credit Risk dataset (https://www.kaggle.com/datasets/laotse/credit-risk-dataset).<br>
The Neural Network was built without using any external libraries. The NN uses the sigmoid activation function: f(x) = (1/(1+e^-x).<br>
K-Cross validation with 5 folds was used in the training process for a better estimate of the models performance and 25 epochs are used per fold. <br>
The output from the output neuron is converted into Binary Classification (0 means loan is rejected, 1 means loan is accepted). <br>
To test the model, one can test any combination of epochs, learning rate, hidden layer neurons and folds. I found the optimal combination to be 25 epochs, a learning rate of 0.05, 15 hidden layer neurons and 5 folds. This keeps the model from overfitting while mainaining high precision, accuracy and true positive predictions.<br>

## Output
The output of the model prints out the following: 
1. The loss and accuracy of each epoch in the following format: Epoch Number - Loss: Double, Accuracy: Double <br>
2. The accuracy of predictions in the fold: Accuracy: <em> Double </em> <br>
3. The precision of the model in the fold: Precision: <em> Double </em> <br>
4. The recall of the model in the fold: Recall: <em> Double </em> <br>
5. The F1 score of the model in the fold: F1 Score: <em> Double </em> <br>
6. The number of True Positives, True Negatives, False Positives, False Negatives in the fold:<br>
True Positives (TP):<em> Number </em> <br>
True Negatives (TN):<em> Number </em> <br>
False Positives (FP):<em> Number </em> <br>
False Negatives (FN):<em> Number </em> <br>
7. A graph plotting the accuracy vs loss per epoch in each fold: <br>
<img width="1023" alt="Screenshot 2024-09-03 at 2 42 07 PM" src="https://github.com/user-attachments/assets/749da9a6-7f0b-4f48-b2aa-8c3c0cd16872">
All of these factors allow for indepth analysis of the models performance any given parameter choice. <br>

## How to download

