package net;

import net.domain.BaseDomain;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public  abstract class BaseObservable<T>implements  Observer<T>
{
    @Override
    public void onSubscribe(Disposable d)
    {

    }


    @Override
    public void onError(Throwable e)
    {

    }

    @Override
    public void onComplete()
    {

    }
}
