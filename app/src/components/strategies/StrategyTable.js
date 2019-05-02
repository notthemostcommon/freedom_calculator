import React, { useState } from 'react'
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const styles = theme => ({
    root: {
      width: '100%',
      marginTop: theme.spacing.unit * 3,
      overflowX: 'auto',
    },
    table: {
      minWidth: 700,
    },
  });

const StrategyTable = (props) =>{

  return (
    <Paper >
    <Table>
      <TableHead>
        <TableRow>
          <TableCell>Creditor</TableCell>
          <TableCell align="right">Original Balance</TableCell>
          <TableCell align="right">Interest Paid</TableCell>
          <TableCell align="right">Months to Pay Off</TableCell>
          <TableCell align="right">Final Payment Month</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {props.data.map( (data, i) => (
    
            <TableRow key={i}>
                <TableCell component="th" scope="row">{data.get("debtName")}</TableCell>
                <TableCell align="right">{data.get("originalBalance")}</TableCell>
                <TableCell align="right">{data.get("accruedInterest")}</TableCell>
                <TableCell align="right">{data.get("monthsOfPayments")}</TableCell>
                <TableCell align="right">{data.get("finalPaymentMonth")}</TableCell> 
            </TableRow>))
        }
        <TableRow>
            <TableCell rowSpan={1} />
            <TableCell colSpan={1}>Total Interest Paid</TableCell>
            <TableCell align="right">{props.interest}</TableCell>
            <TableCell colSpan={2} />
          </TableRow>
        </TableBody>
    </Table>
  </Paper>

  )
}

export default withStyles(styles)(StrategyTable); 
