import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import { Link as RouterLink } from 'react-router-dom';
import Link from '@material-ui/core/Link';

const useStyles = makeStyles({
  root: {
    minWidth: 275,
  },
});

export default function ProductListItem(props) {

  const classes = useStyles();

  return (
      <Box m={2}>
        <Card className={classes.root} variant="outlined">
          <CardContent>
            <Grid container spacing={3}>
              <Grid item>
                Just imagine i am a product picture
              </Grid>
              <Grid item xs={12} sm container>
                <Grid item container direction="column" spacing={2}>
                  <Grid item xs>
                    <Typography gutterBottom variant="subtitle1">
                      Name: {props.product.name}
                    </Typography>
                    <Typography variant="body2" gutterBottom>
                      Price: {props.product.price} {props.product.currency.abbreviation}
                    </Typography>
                    <Typography variant="body2" color="textSecondary">
                      ID: {props.product.domainId}
                    </Typography>
                  </Grid>
                  <Grid item>
                    <Link component={RouterLink} to={{ pathname: '/details/' +
                          props.product.domainId, state: { product: props.product} }}>
                      Details
                    </Link>
                  </Grid>
                </Grid>
              </Grid>
            </Grid>
          </CardContent>
        </Card>
      </Box>
  );
}
